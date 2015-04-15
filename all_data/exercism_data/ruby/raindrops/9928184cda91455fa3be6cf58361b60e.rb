class Raindrops
  def self.convert(number)
    self.new(number).convert
  end

  def initialize(number)
    @number = number
  end

  def convert
    @soundified_number = ""
    plingify
    plangify
    plongify
    abandon_sound_dream_if_necessary
    soundified_number
  end

  private

  attr_reader :number
  attr_accessor :soundified_number

  def plingify
    soundified_number << "Pling" if plingy?
  end

  def plangify
    soundified_number << "Plang" if plangy?
  end

  def plongify
    soundified_number << "Plong" if plongy?
  end

  def abandon_sound_dream_if_necessary
    soundified_number << number.to_s if soundified_number.empty?
  end

  def plingy?
    (number % 3).zero?
  end

  def plangy?
    (number % 5).zero?
  end

  def plongy?
    (number % 7).zero?
  end
end
