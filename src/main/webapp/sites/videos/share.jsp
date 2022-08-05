<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>


<div class="offset-3 col-6">
	<form action="" method="post">
		<div class="card">
			<div class="card-herder">Send Video to Your Friends</div>
			<div class="card-body">
				<div class="form-group">
					<label for="email">Your Friends' emails:</label> <input type="text"
						class="form-control" name="email" id="email"
						aria-describedby="emailHelperId" placeholder="Email"> <small
						id="emailHelperId" class="form-text text-muted"> Email is
						required </small>
				</div>
			</div>
			<div class="card-footer">
				<button class="btn btn-success">Send</button>
			</div>
		</div>

	</form>
</div>