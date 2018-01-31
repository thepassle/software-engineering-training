<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>

<head>
	<style type="text/css">
		*{
			margin: 0;
			padding: 0;
		}

		button, input[type="submit"], input[type="reset"] {
		    background: none !important;
		    color: inherit;
		    border: none;
		    padding: 0! important;
		    font: inherit;
		    cursor: pointer;
		    outline: inherit !important;
		}

		#wrapper{
			width: 100vw;
			height: 100vh;
		}

		.p2{
			background-color: red;
			outline: solid 2px black;
		}

		.p1{
			background-color: blue;
			outline: solid 2px black;
		}

		.p1:hover{
			opacity: 0.8;
		}

		.p2:hover{
			opacity: 0.8;
		}

		#kalahap1, #kalahap2{
			height:100vh;
			width: calc(100vw/8);
			float: left;
			line-height: 100vh;
			text-align: center;
			font-size: 15vh;
			font-family: sans-serif;
		}

		#pit1, #pit2, #pit3, #pit4, #pit5, #pit6, #pit7, #pit8, #pit9, #pit10, #pit11, #pit12{
			height: 50vh;
			width: calc(100vw/8);
			float: left;
			line-height: 50vh;
			text-align: center;
			font-size: 15vh;
			font-family: sans-serif;
		}

		#pitwrapper{
			height: 100vh;
			width: calc(12.5vw * 6);
			float: left;
		}

	</style>
</head>

<body>

<div id="wrapper">
	<div id="kalahap2" class="p2">
                ${sessionScope.pit.getOpposite().getKalaha().getContents()}
	</div>

	<div id="pitwrapper">
		<div id="pit7" class="p2">
            <form action="handleMove.jsp" method="post">
                <input name="12" type="submit" value=${sessionScope.pit.getOpposite().getContents()}>
            </form>
		</div>
		<div id="pit8" class="p2">
            <form action="handleMove.jsp" method="post">
                <input name="11" type="submit" value=${sessionScope.pit.getNeighbour().getOpposite().getContents()}>
            </form>
		</div>
		<div id="pit9" class="p2">
            <form action="handleMove.jsp" method="post">
                <input name="10" type="submit" value=${sessionScope.pit.getNeighbour().getNeighbour().getOpposite().getContents()}>
            </form>
		</div>
		<div id="pit10" class="p2">
            <form action="handleMove.jsp" method="post">
                <input name="9" type="submit" value=${sessionScope.pit.getNeighbour().getNeighbour().getNeighbour().getOpposite().getContents()}>
            </form>
		</div>
		<div id="pit11" class="p2">
            <form action="handleMove.jsp" method="post">
                <input name="8" type="submit" value=${sessionScope.pit.getNeighbour().getNeighbour().getNeighbour().getNeighbour().getOpposite().getContents()}>
            </form>
		</div>
		<div id="pit7" class="p2">
            <form action="handleMove.jsp" method="post">
                <input name="7" type="submit" value=${sessionScope.pit.getNeighbour().getNeighbour().getNeighbour().getNeighbour().getNeighbour().getOpposite().getContents()}>
            </form>
		</div>

		<div id="pit1" class="p1">
            <form action="handleMove.jsp" method="post">
                <input name="0" type="submit" value=${sessionScope.pit.getContents()}>
            </form>
		</div>

		<div id="pit2" class="p1">
            <form action="handleMove.jsp" method="post">
                <input name="1" type="submit" value=${sessionScope.pit.getNeighbour().getContents()}>
            </form>
		</div>
		<div id="pit3" class="p1">
            <form action="handleMove.jsp" method="post">
                <input name="2" type="submit" value=${sessionScope.pit.getNeighbour().getNeighbour().getContents()}>
            </form>
		</div>
		<div id="pit4" class="p1">
            <form action="handleMove.jsp" method="post">
                <input name="3" type="submit" value=${sessionScope.pit.getNeighbour().getNeighbour().getNeighbour().getContents()}>
            </form>
		</div>
		<div id="pit5" class="p1">
            <form action="handleMove.jsp" method="post">
                <input name="4" type="submit" value=			${sessionScope.pit.getNeighbour().getNeighbour().getNeighbour().getNeighbour().getContents()}>
            </form>
		</div>
		<div id="pit6" class="p1">
            <form action="handleMove.jsp" method="post">
                <input name="5" type="submit" value=			${sessionScope.pit.getNeighbour().getNeighbour().getNeighbour().getNeighbour().getNeighbour().getContents()}>
            </form>
		</div>
	</div>

	<div id="kalahap1" class="p1">
				${sessionScope.pit.getKalaha().getContents()}
	</div>


</div>

</body>

