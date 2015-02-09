def combine_anagrams(words)
 an_words = []
 wt = words
 until wt == [] do
  w1 = wt.delete_at(0)
  gr = [w1]
  wt.each do |w2|
   if w1.downcase.split(//).sort==w2.downcase.split(//).sort then
    gr << w2
   end
  end
  gr.each do |w1| 
   wt.delete(w1) 
  end
  an_words << gr
 end
 return an_words
end
