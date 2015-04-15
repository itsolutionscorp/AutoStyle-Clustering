class Proverb
  
  def initialize *words
    @proverb = []
    arrange words
  end

  def to_s
    @proverb.join("\n")
  end

  private

  def arrange words
    qualifier = ""
    
    words.each { |item| qualifier = words.delete(item)[:qualifier] if item.is_a? Hash }
    words.each_cons(2) {|a| @proverb << "For want of a #{a[0]} the #{a[1]} was lost."}

    if qualifier.empty?
      @proverb << "And all for the want of a #{words.first}."
    else
      @proverb << "And all for the want of a #{qualifier} #{words.first}."
    end
  end

end
