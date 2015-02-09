# (C) Wojciech Wroblewski ww@bcteam.org
# 2012.02.27 
# saas-class.org - assignment 1 - part 3

def combine_anagrams(words)
  outp = Hash.new;
  words.each{|w| idx = w.downcase.chars.to_a.sort.join; if outp[idx] == nil then outp[idx] = Array.new; end; outp[idx].push(w); };
  return outp.values ;
end

# data = ['Cars', 'for', 'potatoes', 'racs', 'four','scar', 'creams', 'scream'];  
# puts  combine_anagrams(data);