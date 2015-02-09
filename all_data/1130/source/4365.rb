
module HW1_P3
	def combine_anagrams(words)
		return if words.nil? or words.class != Array #or words.size == 0
		h = Hash.new
		h1 = Hash.new
		h2 = Hash.new

		words.each do |word|
			count = 0
			array = h[word.length]
			if !h1.has_key?(word)
				array.nil? ? h[word.length] = [] << h1[word] = word.downcase.scan(/./).sort.join : h[word.length] = array << h1[word] = word.downcase.scan(/./).sort.join
			else
				count = h2[word] if h2.has_key?(word)
				h2[word] = count + 1
			end	
		end	
		

		arr = []
		h.values.each do |same_size_words_array|
			same_size_words_array.uniq.each do |value|
				arr << h1.map{ |k,v| v==value ? k : nil }.compact 
			end
		end	

		h1.keys.each do |key|
			if h2.has_key?(key)
				for i in 1..h2[key]
					arr.each do |sub_array|
						sub_array << key if sub_array.include?(key)
					end	
				end
				h2.delete(key)
			end	
		end	
		arr
	end
end

include HW1_P3
