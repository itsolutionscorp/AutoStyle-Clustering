require 'date'
require 'Forwardable'

class Year
  extend Forwardable

  def initialize(year)
    @date = Date.new(year, 1, 1)
  end

  def_delegator :@date, :leap?
end
