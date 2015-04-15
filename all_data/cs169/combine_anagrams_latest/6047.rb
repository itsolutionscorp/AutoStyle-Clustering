def combine_anagrams(words)
include Enumerable
ws = Hash[]
newarray =[]
words.each{|w| e = w.downcase.chars.sort.join
				         if ws.has_key? e then ws[e] << w else ws[e] = [w] end 
				        
				        }

 ws.each_value {|v|newarray <<v }
return newarray
end

words = ['Cars','for','potatoes','racs','four','scar','creams','scream']
puts combine_anagrams(words).inspect

puts words.inspect