def combine_anagrams(words)


words.flatten!
words.compact!

arreglo_general = Array.new
tabla = Hash.new 

	words.each do |palabra|
		palabra.downcase!
		clave =  palabra.split("").sort.join("")
		
		tabla[clave] = tabla[clave].to_a.push(palabra).uniq 

	end

tabla.each {|key, value|

	arreglo_general << tabla.values_at(key)  

}

arreglo_general

end