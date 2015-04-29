class Anagram
  def initialize source
    @raw_source = source.downcase
    @source = standard_form @raw_source
  end

  def match targets
    targets.select do |target|
      raw_target = target.downcase
      @source == standard_form(raw_target) && @raw_source != raw_target
    end
  end

  def standard_form word
    word.split("").sort
  end
end
