

def combine_anagrams(words)
  
  h = Hash.new
  
  words.each do |w|
    ary = Array.new
    if h[w.downcase.split(//).sort.join] == nil
      h[w.downcase.split(//).sort.join] = ary << w
    else
      ary = h[w.downcase.split(//).sort.join]
      ary << w
      h[w.downcase.split(//).sort.join] = ary
    end
  end

  h.values
end
