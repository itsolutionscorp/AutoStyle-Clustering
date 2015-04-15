class Robot
  ALPHAS = 'abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ'.chars
  NUMBERS = '0123456789'.chars

  def self.generate_name
    [ ALPHAS.sample,
      ALPHAS.sample,
      NUMBERS.sample,
      NUMBERS.sample,
      NUMBERS.sample ].join('')
  end

  def name
    @name ||= Robot.generate_name
  end

  def reset
    @name = nil
  end
end
