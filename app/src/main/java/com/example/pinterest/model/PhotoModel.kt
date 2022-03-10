package com.example.pinterest.model

import java.io.Serializable

typealias Welcome = ArrayList<PhotoModel>

    data class PhotoModel (
        val id: String,
        val createdAt: String,
        val updatedAt: String,
        val promotedAt: String? = null,
        val width: Long,
        val height: Long,
        val color: String,
        val blurHash: String,
        val description: String? = null,
        val altDescription: Any? = null,
        val urls: Urls,
        val links: WelcomeLinks,
        val categories: List<Any?>,
        val likes: Long,
        val likedByUser: Boolean,
        val currentUserCollections: List<Any?>,
        val sponsorship: Sponsorship? = null,
        val topicSubmissions: TopicSubmissions,
        val user: User
    ):Serializable

    data class WelcomeLinks (
        val self: String,
        val html: String,
        val download: String,
        val downloadLocation: String
    ):Serializable

    data class Sponsorship (
        val impressionUrls: List<String>,
        val tagline: String,
        val taglineURL: String,
        val sponsor: User
    ):Serializable

    data class User (
        val id: String,
        val updatedAt: String,
        val username: String,
        val name: String,
        val firstName: String,
        val lastName: String? = null,
        val twitterUsername: String,
        val portfolioURL: String? = null,
        val bio: String,
        val location: String? = null,
        val links: UserLinks,
        val profileImage: ProfileImage,
        val instagramUsername: String,
        val totalCollections: Long,
        val totalLikes: Long,
        val totalPhotos: Long,
        val acceptedTos: Boolean,
        val forHire: Boolean,
        val social: Social
    ):Serializable

    data class UserLinks (
        val self: String,
        val html: String,
        val photos: String,
        val likes: String,
        val portfolio: String,
        val following: String,
        val followers: String
    ):Serializable

    data class ProfileImage (
        val small: String,
        val medium: String,
        val large: String
    ):Serializable

    data class Social (
        val instagramUsername: String,
        val portfolioURL: String? = null,
        val twitterUsername: String,
        val paypalEmail: Any? = null
    ):Serializable

    class TopicSubmissions()

    data class Urls (
        val raw: String,
        val full: String,
        val regular: String,
        val small: String,
        val thumb: String,
        val smallS3: String
    ):Serializable
