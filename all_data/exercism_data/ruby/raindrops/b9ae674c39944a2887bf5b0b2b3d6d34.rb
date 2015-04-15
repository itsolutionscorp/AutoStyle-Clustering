class Raindrops
  def self.convert(drops)
    case
    when divisible_by_3_7_5?(drops)
      "PlingPlangPlong"
    when divisible_by_3_7?(drops)
      "PlingPlong"
    when divisible_by_3_5?(drops)
      "PlingPlang"
    when divisible_by_7_5?(drops)
      "PlangPlong"
    when divisible_by_7?(drops)
      "Plong"
    when divisible_by_5?(drops)
      "Plang"
    when divisible_by_3?(drops)
      "Pling"
    else
      drops.to_s
    end
  end

  private

  def self.divisible_by_3_7_5?(drops)
    (drops % 3 == 0) && (drops % 7 == 0) && (drops % 5 == 0)
  end

  def self.divisible_by_3_7?(drops)
    (drops % 3 == 0) && (drops % 7 == 0)
  end

  def self.divisible_by_3_5?(drops)
    (drops % 3 == 0) && (drops % 5 == 0)
  end

  def self.divisible_by_7_5?(drops)
    (drops % 7 == 0) && (drops % 5 == 0)
  end

  def self.divisible_by_7?(drops)
    (drops % 7 == 0)
  end

  def self.divisible_by_5?(drops)
    (drops % 5 == 0)
  end

  def self.divisible_by_3?(drops)
    (drops % 3 == 0)
  end

end
