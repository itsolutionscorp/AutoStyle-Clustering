class ETL

  attr_reader :new_format, :old_format

  def self.transform(old_letters)
    self.new(old_letters).transform.new_format
  end

  def initialize(old_format)
    @old_format = old_format
    @new_format = {}
  end

  def transform
    @old_format.collect do |score, letters|
      score_letters_to_pair(score, letters).inject(@new_format) do |m,k|
        m[k[0]] = k[1]
        m
      end
    end
    self
  end

  def score_letters_to_pair(score, letters)
    letters.collect{|x| [x.downcase, score]}
  end
end
