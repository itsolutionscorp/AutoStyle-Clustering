class Bob
  def initialize
    @brain           = Brain.new
    @auditory_system = AuditorySystem.new
    @larynx          = Larynx.new
  end
  
  attr_reader :brain, :auditory_system, :larynx
  
  def hey(acoustic_waves)
    larynx.speak(brain.auditory_cortex(
             auditory_system.eardrum(acoustic_waves)))
  end
end

class Larynx
  def speak(speech_signal_from_brain)
    "#{speech_signal_from_brain}"
  end
end

class Brain
  def initialize
    @deep_limbic_system = { emotion: "chill"}
  end
  
  attr_accessor :deep_limbic_system
  
  def auditory_cortex(auditory_nerve_signal)
    if auditory_nerve_signal == "Empty"
      wernickes_area("No auditory signal")
    else
      wernickes_area(auditory_nerve_signal)
    end
  end
  
  def wernickes_area(message_from_auditory_cortex)
    m_f_a_c = message_from_auditory_cortex
    case
    when m_f_a_c == "No auditory signal"
      frontal_lobe("No auditory signal")
    when m_f_a_c == m_f_a_c.upcase
      frontal_lobe("Person is yelling")
    when m_f_a_c.end_with?("?")
      frontal_lobe("Person is asking a question?")
    else
      frontal_lobe("Person is talking we don't care")
    end
  end
  
  def frontal_lobe(message_from_wernickes_area)
    case message_from_wernickes_area
    when "No auditory signal"
      deep_limbic_system[:emotion] = "ignored"
    when "Person is yelling"
      deep_limbic_system[:emotion] = "angry"
    when "Person is asking a question?"
      deep_limbic_system[:emotion] = "questioned"
    when "Person is talking we don't care"
      deep_limbic_system[:emotion] = "chill"
    end
    brocas_area
  end
  
  def brocas_area
    case deep_limbic_system[:emotion]
    when "ignored"
      "Fine. Be that way."
    when "angry"
      "Woah, chill out!"
    when "questioned"
      "Sure."
    when "chill"
      "Whatever."
    end
  end
end

class AuditorySystem
  def eardrum(acoustic_waves)
    cochlea(acoustic_waves)
  end
  
  def cochlea(acoustic_waves)
    if acoustic_waves.nil? || acoustic_waves.empty?
      "Empty"
    else
      acoustic_waves
    end
  end
end
