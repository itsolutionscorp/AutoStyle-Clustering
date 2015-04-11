class Allergies

  SCORE = {
    1 => "eggs",
    2 => "peanuts",
    4 => "shellfish",
    8 => "strawberries",
    16 => "tomatoes",
    32 => "chocolate",
    64 => "pollen",
    128 => "cats"
  }

  def initialize(score)
    @score = score
    if @score < 1
      @allergies = []
    else
      @allergies = find(score)
    end
  end

  def allergic_to?(allergen)
    @allergies.include? allergen
  end
  
  def list
    reversed_score = Hash[SCORE.map { |k, v| [v, k] }]
    @allergies.sort{ |a,b| reversed_score[a]<=>reversed_score[b] }
  end  

  private
  def find(score)
    allergies = []
    s = SCORE.keys.sort{ |a,b| b<=>a }
    i = 0
    while (score != 0)
      if score >= s[i]  then
        allergies << SCORE[s[i]] unless allergies.include? SCORE[s[i]]
        score = score - s[i]
      else
        i+=1
      end
    end
    allergies
  end

end
