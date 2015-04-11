class Hash
  def fmap
    newMap = {}
    each_pair do |k,v|
      newMap[k] = yield v
    end
    newMap
  end
end

class Phrase

  def initialize(phrase)
    @phrase = phrase
  end

  # safe to cache because we do not allow mutation of @phrase.
  def word_count
    @hash ||= @phrase.downcase.scan(/[[:alnum:]]+/).group_by{|x| x}.fmap &:length
  end

end
