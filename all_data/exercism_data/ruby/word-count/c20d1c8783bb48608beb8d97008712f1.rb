class Counter < Hash
  def add(item)
    k = key_for(item)
    self[k] = count_for(k) + 1
  end
  
  private
  
  def count_for(k)
    (has_key? k) ? self[k] : 0
  end
  
  def key_for(item)
  	item.to_s.strip.downcase
  end
end

class Phrase
  def initialize(input)
    @input = input
  end
  
  def word_count
    split(@input).each_with_object(Counter.new) { |w, counter| counter.add w }
  end
	
  private
	
  def split(input)
    input.scan(/[a-zA-Z0-9]+/)
  end
end
