
<nav class="navbar navbar-expand-lg navbar-light bg-light">
  <div class="collapse navbar-collapse" id="navbarColor03">
    <ul class="navbar-nav mr-auto d-flex justify-content-between" style="width: 100%">
      <div class="row ml-2">
        <li class="nav-item">
          <a class="nav-link" onclick="location.href='/buyer'">livres
          </a>
        </li>  
        <li class="nav-item">
          <a class="nav-link" onclick="location.href='/users/list'">utilisateurs
          </a>
        </li>  
      </div>
      <li>
        <a class="nav-link">connecté en tant que ${firstname} ${lastname}</a>
      </li>
      <div class="row">
       <li class="nav-item align-content-end">
          <a class="nav-link">${wallet} €</a>
        </li>
        <li class="nav-item align-content-end">
          <a type="button" class="nav-link font-weight-bolder" onclick="location.href='/users/${user_id}'">mon compte
          </a>
        </li>
        <li class="nav-item align-content-end">
          <a class="nav-link font-weight-bolder" type="button" onclick="location.href='/logout'">Logout
            <span class="sr-only">(current)</span>
          </a>
        </li>
      </div>
    </ul>
  </div>
</nav>

<#if error??>
	<h3>
		${error}
	</h3>
</#if>