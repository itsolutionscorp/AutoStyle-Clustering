  def combine_anagrams(words)
     if words.length == 0
        return
      end
      words.flatten!

      normalized = Hash.new

      words.each do |word|
        key   = word
        value = word.downcase.chars.sort.join
        normalized.merge!({key => value})
      end

      sorted_anagrams = normalized.sort_by {|key, value| value}
      seed = sorted_anagrams[0][1]
      last = sorted_anagrams.last[1]

      answer    = Array.new
      collector = Array.new
      anagram_length = sorted_anagrams.length
      counter = 1

      sorted_anagrams.each do |key, value|
        if value == seed #repeat value encountered
          collector << key
        else #new value encountered
          answer << collector
          collector = Array.new
          seed = value
          collector << key
        end

        if anagram_length == counter && anagram_length > 1
          answer << collector
        end
        counter += 1
      end
      answer
  end