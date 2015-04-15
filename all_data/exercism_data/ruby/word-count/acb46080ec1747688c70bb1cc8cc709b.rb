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
    @hash = phrase.downcase.split(/[^[:alnum:]]+/).group_by{|x| x}.fmap{|v| v.length}
  end

  def word_count
    @hash
  end

end
