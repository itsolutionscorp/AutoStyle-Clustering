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

  TRANSLATABLES = {
    3 => 'Pling',
    5 => 'Plang',
    7 => 'Plong',
  }

  def generate_sources_from number
    TRANSLATABLES.select { |translatable, _| number.modulo(translatable).zero? }
  end

  def to_target sources
    sources.map(&:last).join
  end
end
