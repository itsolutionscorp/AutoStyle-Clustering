class Proverb

  def initialize(*nouns, qualifier:  nil)
    @nouns = nouns
    @qualifier = qualifier
  end

  def to_s
    repeat_chorus
  end

  private 

  attr_reader :nouns, :qualifier

  def repeat_chorus
    chorus = []

    repetitions.times do |i|
       chorus << "For want of a #{nouns[i]} the #{nouns[i + 1]} was lost."
    end

    chorus.join("\n") + final_sequence
  end

  def repetitions
    nouns.length - 1
  end

  def final_sequence
    if qualifier
      "\nAnd all for the want of a #{qualifier} #{nouns.first}." 
    else
      "\nAnd all for the want of a #{nouns.first}." 
    end
  end

end
