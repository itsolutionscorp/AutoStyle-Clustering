def combine_anagrams(list)

    anagrams = Hash.new

    list.each do |w|
        (anagrams[w.downcase.chars.sort(&:casecmp).join] ||= [] ) << w
    end

    ret = []

    anagrams.keys.each do |k|
      ret << anagrams[k]
    end

    return ret

end

