class Allergies
  def initialize(score)
    @score = score
  end

  def allergic_to?(substance)
    @score & substance_score(substance) > 0
  end

  def list
    SUBSTANCES.map { |s,| s if allergic_to? s }.compact
  end

private

  SUBSTANCES = { "eggs"         =>   1,
                 "peanuts"      =>   2, 
                 "shellfish"    =>   4, 
                 "strawberries" =>   8,
                 "tomatoes"     =>  16,
                 "chocolate"    =>  32,
                 "pollen"       =>  64,
                 "cats"         => 128 }

  def substance_score(substance)
    SUBSTANCES[substance] || 0
  end

end
