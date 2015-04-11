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
    if sources.any?
      to_target sources
    else
      number.to_s
    end
  end

  private

  TRANSLATABLES = [3, 5, 7]

  def generate_sources_from number
    TRANSLATABLES.select { |translatable| number.modulo(translatable).zero? }
  end

  def to_target sources
    sources.map { |source| translation_to_target source }.join
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
