def combine_anagrams(words)
  tmp = Hash.new;
  words.each do |w|
    s = w.downcase.chars.sort.join;
    if (tmp[s].class==Array)
      tmp[s].push(w);
    else tmp[s]=[w];
    end
  end
  ret = Array.new;
#puts tmp;
  tmp.each {|key, value| ret.push(value)};
  return ret;
end