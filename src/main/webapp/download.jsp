<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!doctype html>
<html lang="en">
  <head>
  
    <!-- Required meta tags -->
  
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.0.0/dist/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">

    <title>Hello, world!</title>
  </head>
  <body>
  
 
     <h1>Uploaded Files</h1>
     
    <h4>${success}</h4>
     <form action="/all" method="GET">
     
                                             <div class="card-block">
                                                <div class="dt-responsive table-responsive">
                                                    <table id="compact" class="table compact table-striped table-bordered nowrap">
                                                        <thead>
                                                            <tr>
																<th><center>File Id</center></th>
																<th><center>File Name</center></th>
																<th><center>Upload Date</center></th>
																<th><center>Create On</center></th>
																<th><center>Action</center></th>
																
															</tr>
                                                        </thead>
                                                        <tbody>
                                                       <c:forEach items="${ListofFile}" var="j">
								<tr>
									<td><center>${j.id}</center></td>
									<td><center>${j.fileName}</center></td>
									<td><center>${j.fileType}</center></td>
									<td><center>${j.timestamp}</center></td>
									<td scope="row">
									
					<a href="download/${j.fileName}" class="btn btn-warning">Download</a>
				<a href="view/${j.fileName}" class="btn btn-warning">View</a>

								</tr>
							</c:forEach>
                                                             
                                                        </tbody>
                                                         
                                                    </table>
                                                </div>
                                            </div>
    
     
     </form>
     
     
     
     
    
    

    <!-- Optional JavaScript -->
    <!-- jQuery first, then Popper.js, then Bootstrap JS -->
    <script src="https://code.jquery.com/jquery-3.2.1.slim.min.js" integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN" crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/popper.js@1.12.9/dist/umd/popper.min.js" integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q" crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.0.0/dist/js/bootstrap.min.js" integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous"></script>
  </body>
</html>