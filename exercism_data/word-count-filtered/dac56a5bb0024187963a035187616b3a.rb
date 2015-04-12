class Phrase

  def initialize(sent)
    @sent_arr = sent.scan(/\w+'\w+|\w+/i)
  end

  def word_count

    count_hash = {}
    @sent_arr.each do |word|
      if count_hash[word.downcase] == nil
        count_hash[word.downcase] = 1
      else
        count_hash[word.downcase] += 1
      end
    end

    return count_hash
  end

end
