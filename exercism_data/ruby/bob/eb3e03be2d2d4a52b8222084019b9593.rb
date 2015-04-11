class Bob
  def hey strand
    answer(Phrase.new(strand))
  end
  def answer phrase
    case
    when phrase.quiet? then 'Fine. Be that way!'
    when phrase.shout? then 'Woah, chill out!'
    when phrase.quiz? then 'Sure.'
    else
      'Whatever.'
    end
  end
end

class Phrase
  attr_accessor :strand
  def initialize strand
    @strand = strand.to_s.strip
  end
  def quiz?
    strand.end_with?('?')
  end
  def shout?
    strand =~ /[A-Z]/ && strand.upcase == strand
  end
  def quiet?
    strand.empty?
  end
end
