def combine_anagrams(words)
  groups = []
  # Para cada palabra
  while words.count > 0 do
  	word = words[0]
  	#Crear un grupo
  	group = words.select do |other_word|
	  	#Comprobar si hay otra que sea un anagrama
	  	word.downcase.split(%r//).sort == other_word.downcase.split(%r//).sort
	end
  	
  	#Colocarlas juntas en el grupo
  	groups << group
  	#Borrar la encontrada del array original para que no se repita la operación
  	words -= group
   end
  groups
end