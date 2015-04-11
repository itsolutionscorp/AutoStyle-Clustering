class Proverb
  def initialize(*args)
    @qualifier = args.last.is_a?(Hash) ? args.pop[:qualifier] : false
    @terms     = args
  end

  def to_s
    string = ""

    @terms.each_with_index do |term, i|
      string += "For want of a #{term} the #{@terms[i+1]} was lost.\n" unless @terms[i+1].nil?
    end

    if @qualifier
      string += "And all for the want of a #{@qualifier} #{@terms.first}."
    else
      string += "And all for the want of a #{@terms.first}."
    end
  end
end
