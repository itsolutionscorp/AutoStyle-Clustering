def combine_anagrams (words)
  out=Array.new(0)
  words.each do |w1|
    temp=[]
    words.each do |w2|
      if(w2.downcase.split(//).sort==w1.downcase.split(//).sort)
        temp.push(w2)
    end
  end
  out.push(temp)
end
  return out.uniq
end
