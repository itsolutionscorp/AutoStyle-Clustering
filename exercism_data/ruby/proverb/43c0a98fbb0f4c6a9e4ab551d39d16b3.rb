class Proverb
  attr_reader :words, :qualifier
  def initialize *args
    @qualifier =  args.pop[:qualifier] + ' ' if args.last.is_a? Hash
    @words = args
  end

  def to_s
    words[0...-1].each_index.with_object('') do |i, a|
      a << "For want of a #{words[i]} the #{words[i + 1]} was lost.\n"
    end << "And all for the want of a #{qualifier}#{words.first}."
  end
end
