def extract_key(string)
	string = string.downcase.split('').sort.join	
end

def combine_anagrams(string)
  h = Hash.new
  string.each { |s|
  key = extract_key s
	
		if h[key].nil? then
			h[key] = Array.new
		end
		h[key] << s
  }
  
  h.values
  
end

=begin
p combine_anagrams(['cars', 'for', 'potatoes', 'racs', 'four','scar', 'creams', 'scream'])
=end

