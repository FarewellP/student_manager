<%@ page language="java" import="java.util.*,dbutil.*,entity.*,model.*"
	pageEncoding="gbk"%>


<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<title>�޸�ѧ����Ϣ</title>
	</head>

	<body>
		<center>
			<h1>
				�޸�ѧ����Ϣ
			</h1>
			<form action="DoUpdatStudenteservlet.do?id=${student.id}" method="post">
				<p>
					ѧ��: ${student.id}
				</p>
				<p>
<%--					<img width="50px" height="50px" src="/studentdemo_war_exploded/uploads/${student.imgUrl}">--%>
				<img width="150px" height="150px" src="/studentdemo_war_exploded/uploads/${student.imgUrl}">
				</p>
				<p>
					����:
					<input type="text" name="name" value="${student.name}" />
					<br>
				</p>
				<p>
					�Ա�:
					<input type="text" name="sex" value="${student.sex}" />
					<br>
				</p>
				<p>
					����:
					<input type="text" name="age" value="${student.age}" />
					<br>
				</p>
				<p>
					�༶:
					<input type="text" name="grade" value="${student.grade}" />
					<br>
				</p>
				<p>
					�ɼ�:
					<input type="text" name="score" value="${student.score}" />
					<br>
				</p>
				<input type="submit" value="�޸�" />
				<input type="reset" value="����" />
			</form>
			<form action="IMGFile?id=${student.id}" enctype="multipart/form-data" method="post">
				<input type="file" name="file"  accept="image/*"/>
				<input type="submit" value="�ϴ�"></a>
			</form>

		</center>
	</body>
</html>
