class Bob
  def hey(phrase)
    saying = Saying.new phrase
    
    if saying.statement?
      'Whatever.'      
    elsif saying.yelling?
      'Woah, chill out!'  
    elsif saying.question?
      'Sure.'
    elsif saying.empty?
      'Fine. Be that way!'
    end
  end
  
end

class Saying
  [:empty, :yelling, :question, :statement].each do |name|
    define_method("#{name}?") { @kind == name }
  end
  
  def initialize(phrase)
    @phrase = phrase.to_s
    @kind   = detect_kind
  end
  
protected

  def detect_kind
    if @phrase.strip == ''
      :empty
    elsif @phrase == @phrase.upcase
      :yelling
    elsif @phrase.end_with? '?'
      :question
    else
      :statement
    end
  end
end
