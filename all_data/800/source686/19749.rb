
  def combine_anagrams(words)
    myhash = {}
    aout = words.each{|aword| 
      akey = aword.downcase.chars.sort.join
      if myhash[akey].nil?
        myhash[akey] = [aword]
      else
        myhash[akey] << aword
      end
    } # words.each
    myhash.values
  end


