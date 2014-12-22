def combine_anagrams(words)
  if words.length == 0 
    return words
  end
  ret = words.clone
  alpha = Array.new()
  ret.each { |w| alpha << alphabetize(w) }
  
  i = 0
  begin 
    tmp = ret[i]
    ret[i] = Array.new()
    ret[i] << tmp
    j = i+1
    begin
      if alpha[j] == alpha[i]
        ret[i] << ret[j]
        ret.delete_at j
        ret.compact!
        alpha.delete_at j
        alpha.compact!
      else
        j += 1
      end
      # print alpha.to_s + " j :: #{j}\n"
    end until j >= alpha.length
    # ret << tmp
    i += 1
    # print "i :: #{i}\n"
  end until i == ret.length
  return ret
end

def alphabetize(word)
    chars = Array.new()
    word.downcase.each_char { |n| chars << n }
    ret = String.new()
    chars.sort!.each { |l| ret = ret + l }
    return ret
end

puts combine_anagrams( [] ).to_s
puts combine_anagrams( ['cars', 'for', 'potatoes', 'racs', 'four','scar', 'creams', 'scream'] ).to_s
#  => output:  [["cars", "racs", "scar"], ["four"], ["for"], ["potatoes"], ["creams", "scream"]]
