require 'prime'

####
# Converts a source to a string
#
# The contents of the string depends on primefactors
# - a factor - is a number that divides into another
#              number without leaving a remainder
# - Primefactor - a factor that is a prime ;-)
#
####
class Raindrops

  def convert number
    sources = generate_sources_from number
    if to_target? sources
      to_target sources
    else
      number.to_s
    end
  end

  private

  TRANSLATABLES = [3, 5, 7]

  def generate_sources_from number
    number.prime_division.map { |prime_factor_set| prime_factor_set.first}
  end

  def to_target? sources
    sources.any? { |source| translatable? source }
  end

  def to_target sources
    sources.map { |source| translation_to_target source }.join
  end

  def translatable? candidate
    TRANSLATABLES.include? candidate
  end

  def translation_to_target candidate
    case candidate
      when 3 then 'Pling'
      when 5 then 'Plang'
      when 7 then 'Plong'
      else nil
    end
  end
end
