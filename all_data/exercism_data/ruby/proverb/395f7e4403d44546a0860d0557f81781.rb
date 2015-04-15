class Proverb
  attr_reader :chain, :qualifier

  def initialize(*args)
    @chain = args
    if @chain.last.is_a?(Hash)
      @qualifier = @chain.pop[:qualifier]
    end
  end

  def to_s
    string = ''
    count = chain.length - 1
    count.times do |i|
      string += "For want of a #{chain[i]} the #{chain[i+1]} was lost.\n"
    end
    string += end_statement
  end

  private
    def end_statement
      if qualifier
        "And all for the want of a #{qualifier} #{chain[0]}."
      else
        "And all for the want of a #{chain[0]}."
      end
    end
end
