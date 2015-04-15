require 'forwardable'

class String
  def to_a
    chars.to_a
  end
end

class AllergyDictionary
  def initialize(allergens_by_code=nil)
    @allergens_by_code =  allergens_by_code || default_allergens_by_code
  end

  def code_for_allergen(allergen)
    @allergens_by_code.find { |k,v| v == allergen }.to_a.first
  end

  def allergen_codes
    @allergens_by_code.keys
  end

  def allergen_for_code(code)
    @allergens_by_code[code]
  end

  private

  def default_allergens_by_code
    {
      1 => 'eggs', 
      2 => 'peanuts', 
      4 => 'shellfish', 
      8 => 'strawberries', 
      16 => 'tomatoes', 
      32 => 'chocolate', 
      64 => 'pollen', 
      128 => 'cats' 
    }
  end
end

class Allergies
  extend Forwardable
  
  attr_reader :score, :score_as_binary, :allergy_dictionary
  def_delegator :@allergy_dictionary, :allergen_for_code
  def_delegator :@allergy_dictionary, :code_for_allergen 
  def_delegator :@allergy_dictionary, :allergen_codes
  
  def initialize(score, options={})
    @score = score.to_i
    @allergy_dictionary = options.fetch(:allergy_dictionary, default_allergy_dictionary)
  end

  def allergic_to?(allergen)
    valid_allergen_code?( code_for_allergen(allergen) )
  end

  def list
    allergen_codes.collect do |code|
      allergen_for_code(code) if valid_allergen_code?(code)
    end.compact
  end

  private

  def valid_allergen_code?(code)
    code&score == code
  end

  def default_allergy_dictionary
    AllergyDictionary.new
  end
end
