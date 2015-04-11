class Words
   def initialize source
    @inventory = source.downcase.split %r{\W}
    @inventory.delete ''
  end

  def count
    word_count = Hash.new(0)

    @inventory.each do |w|
      word_count[w] = word_count[w] + 1
    end
    
    word_count
  end
end
