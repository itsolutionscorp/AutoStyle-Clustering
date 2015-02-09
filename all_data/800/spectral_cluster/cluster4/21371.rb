def combine_anagrams(words)
        result = Array.new
        copy_words = words
        words.each do |x|
                tmp_result = Array.new
                copy_words.each do |y|
                        if x.downcase.chars.sort.join  == y.downcase.chars.sort.join
                        	if result.flatten.include?(y) == false
                        		tmp_result << y
                                end
                        end
                end
                if tmp_result.size > 0
                        result << tmp_result
                end
        end
        result
end
