class Phrase
  def initialize phrase
    @phrase = phrase.downcase
  end

  SEPERATOR_REGEX = /[[:space:],]/

  def word_count
    counts = {}
    @phrase.split(SEPERATOR_REGEX).each do |word|
      word.gsub!(/\W/, "")
      next if word == "" # I forget. Is "" truthy?
      counts[word] = 0 unless counts.has_key? word
      counts[word] += 1
    end
    counts
  end
end
