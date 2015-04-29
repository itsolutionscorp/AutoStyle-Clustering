def combine_anagrams (words)
final = Array.new
n = 0
words.each do |w|
   temp = Array.new
   temp = w.downcase.split(//).sort
   temp.sort
#   puts temp.to_s
#   puts "=====" + n.to_s + "======"
#   puts temp 
   final[n] = temp
   n += 1
end
=begin
len = final.len -1
0.upto(len) do |x|
   word = final[x]
   innerlen = final.len
   innerlen.downto(x+1) do |y|
      if final[x]=final[y]
         final.remove(y)
      end
   end
end
=end
final.uniq!

=begin
n=0
final.each do |x|
   puts "=====" + n.to_s + "======"
   puts x
   n += 1
end
=end

really_final = Array.new
final.each do |x|
   semi_final = Array.new
   words.each do |y|
     temp = y.downcase.split(//).sort
     if (temp == x)
        semi_final.insert(semi_final.length, y)
     end
   end
   really_final.insert(really_final.length, semi_final)
end
really_final.sort
return really_final
end

=begin
words = ['cARS', 'for', 'potatoes', 'racs', 'four','scar', 'creams', 'scream']
#words = ["cars", "for", "potatoes", "racs", "four","scar", "creams", "scream"]

answer = combine_anagrams(words)
puts answer.to_s
=end

