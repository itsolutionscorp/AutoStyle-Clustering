def combine_anagrams(words)
  gr = Hash.new
  words.each do |w|
    #print w.chars.sort.join, "\n"
    gr_key = w.chars.sort.join
    if gr.has_key?(gr_key)
      gr[gr_key] = gr[gr_key] + [w] 
    else
      gr[gr_key] = [w]
    end
  end
  return gr.values
end

