<?php
	$contents = file_get_contents("liste_pharmacie.json");
	$contents = utf8_encode($contents);
	$contents = json_decode($contents, true);

		for($i = 0 ; $i < count($contents); $i++)
		{
			unset($contents[$i]['datasetid']);
			unset($contents[$i]['recordid']);
			unset($contents[$i]['geometry']);
			unset($contents[$i]['record_timestamp']);
			unset($contents[$i]['fields']['departement']);
			unset($contents[$i]['fields']['libdepartement']);
			unset($contents[$i]['fields']['wgs']);
		}
		
	echo "<pre>".json_encode($contents, JSON_PRETTY_PRINT)."</pre>";
?> 