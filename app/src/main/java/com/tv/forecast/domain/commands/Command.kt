package com.tv.forecast.domain.commands

interface Command<T> {
    fun execute(): T?
}