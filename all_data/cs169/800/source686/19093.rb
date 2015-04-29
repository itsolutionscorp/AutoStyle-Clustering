# hw/hw1/lib/p3.rb

  def combine_anagrams(words)
    hsh = {}
    aout = words.each{|wrd| 
      akey = wrd.downcase.chars.sort.join
      if hsh[akey].nil?
        hsh[akey] = [wrd]
      else
        hsh[akey] << wrd
      end
    } # words.each
    return hsh.values
  end # def combine_anagrams()


