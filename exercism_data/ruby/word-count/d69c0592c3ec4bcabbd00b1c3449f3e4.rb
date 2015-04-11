class Phrase
  attr_accessor :strand
  def initialize strand
    @strand = strand
  end
  def word_count
    arg = strand.downcase.scan(/[\w']+/)
    wf = Hash.new(0)
    arg.each { |word| wf[word] += 1 }
    return wf
  end
end
