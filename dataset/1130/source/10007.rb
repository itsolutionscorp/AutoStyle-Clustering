def combine_anagrams (words)
 final_a = Array.new(0)

 words.each do |w|
  tmp_a = Array.new(0)
  tmp_a[0] = w
  final_a.each do |sub_a|

   if (w.downcase.split(//).sort==sub_a[0].downcase.split(//).sort)
    sub_a << tmp_a.delete_at(0)
   end

  end

  if !tmp_a.empty?
   final_a << tmp_a
  end

 end
 return final_a
 
end