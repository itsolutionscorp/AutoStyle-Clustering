class Grains
  @@grains = [nil, 1]

  def square(field)
    return cache(field) if cache(field)
    cache field, 2 * square(field - 1)
  end

  def total
    calculate_all
    @@grains[1..64].inject :+
  end

  private

  def cache(field, value = nil)
    return @@grains[field] if value.nil?
    @@grains[field] = value
  end

  def calculate_all
    square(64)
  end
end
