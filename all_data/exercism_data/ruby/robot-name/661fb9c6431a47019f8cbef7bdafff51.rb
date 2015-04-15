class Robot
  def name
    # Robot names must be two characters followed by three numbers (ccnnn)
    @name ||= 'ccnnn'.gsub(/(c|n)/, {
      'c' => ('A'..'Z').to_a.sample,
      'n' => ('0'..'9').to_a.sample
    })
  end

  def reset
    @name = nil
  end
end
