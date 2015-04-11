module RaindropRefinements
  refine Integer do
    def divisible_by?(x)
      self % x == 0
    end
  end
end

using RaindropRefinements

class Raindrops
  def convert(i)
    response = relevant_sounds(i).join('')
    response.empty? ? i.to_s : response
  end

  private

  def sounds
    {3 => 'Pling', 5 => 'Plang', 7 => 'Plong'}
  end

  def relevant_sounds(i)
    sounds.select{ |prime| i.divisible_by?(prime) }.values
  end
end
