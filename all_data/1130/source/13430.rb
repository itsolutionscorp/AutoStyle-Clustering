def combine_anagrams(input)
  inputs = input.map {|elem| elem = elem.downcase.chars.sort.join }  
  output = Hash.new
  inputs.each_with_index do |in_elem, read_index|
    #puts in_elem, read_index
    if output.has_key?(in_elem)
      output[in_elem] << input[read_index]
    else
      output[in_elem] = [input[read_index]]
    end
  end
  #//puts "--------\n"
  output_list = []
  output.each do |key, elem|
    output_list << elem
  end
  puts output_list
  return output_list
end

inputt = ['cars', 'for', 'potatoes', 'racs', 'four','scar', 'creams', 'scream']

combine_anagrams(inputt)



=begin
def count_words(string)
  string = string.downcase.gsub(/[^a-z ]+/i, '')
	words = string.split( /\s+|\b/ )
	freq = Hash.new(0)
	words.each { |word| freq[word] += 1}

	freq
end
=end