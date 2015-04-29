def combine_anagrams(words)
  an_words = []
  wt = words
  until (wt == []) do
    (w1 = wt.delete_at(0)
    gr = [w1]
    wt.each do |w2|
      (gr << w2) if (w1.downcase.split(//).sort == w2.downcase.split(//).sort)
    end
    gr.each { |w1| wt.delete(w1) }
    (an_words << gr))
  end
  return an_words
end

